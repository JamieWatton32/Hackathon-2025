import csv
import re
import sqlite3
import os

# This script is horrible and is more than likely noT extensible whatsoever. 
# Only meant to be ran once to create the DB schema 

# CHANGE THINGS WITH CAUTION

# Define database file path
db_path = "sqlite/database.db"
# Connect to the database
conn = sqlite3.connect(db_path)
cur = conn.cursor()
# Tables and CSV file paths
tables = ["Classrooms", "Culinary", "Laboratory", "Maintenance", "Offices", "Shops"]
csvs = [
    "data/Classroom Procedure.csv", "data/Culinary Procedure.csv",
    "data/Laboratory Procedure.csv", "data/Maintenance Procedure.csv",
    "data/Office Procedure.csv", "data/Shop Procedure.csv"
]
# Check if database folder exists, create if not
os.makedirs(os.path.dirname(db_path), exist_ok=True)


# Replace whitespace with _ and removes ? from questions to brute force column headers
def sanitize_column_name(column_name):
    return re.sub(r'\W+', '_', column_name.strip())

# This loop handles table creation. Much like the create loop it will (not tested dont trust me) any N number of columns
# Okay to be ran if tables already exist. 
for index, csv_path in enumerate(csvs):
    if not os.path.exists(csv_path):
        raise FileNotFoundError(f"CSV file not found at {csv_path}")

    with open(csv_path, 'r', encoding='utf-8-sig') as fin:
        dr = csv.DictReader(fin)  # Read CSV file
        
        # Sanitize and map column names
        original_columns = dr.fieldnames
        if not original_columns:
            raise ValueError(f"No columns found in CSV file: {csv_path}")
        sanitized_columns = [sanitize_column_name(col) for col in original_columns]
        del sanitized_columns[0] # delete the id column since we want it to be defined as the primary key.

        # Construct CREATE TABLE statement dynamically
        column_definitions = "ID INTEGER PRIMARY KEY, " + ', '.join([f"{col} TEXT" for col in sanitized_columns])
         

        table_name = tables[index]
        create_table_sql = f"CREATE TABLE IF NOT EXISTS {table_name} ({column_definitions});"
        cur.execute(create_table_sql)

# This loop handles row insertion. Much like the create loop it will (not tested dont trust me) any N number of columns
# There is no checking for repeat data so it crashes if theres a repeat key. 
for index, csv_path in enumerate(csvs):
    with open(csv_path, 'r', encoding='utf-8-sig') as fin:
        dr = csv.DictReader(fin)
        
        # Sanitize and map column names
        original_columns = dr.fieldnames
        sanitized_columns = [sanitize_column_name(col) for col in original_columns]
        
        # Prepare placeholders and dynamic SQL query
        columns_sql = ', '.join(sanitized_columns)
        placeholders = ', '.join(['?' for _ in sanitized_columns])
        
        to_db = []
        for row in dr:
            # Map original values to sanitized columns
            values = [re.sub(r'[\[\]"]', "",row[col]) for col in original_columns]
            to_db.append(values)

        # Insert data into tables
        try:
            cur.executemany(
                f"INSERT INTO {tables[index]} ({columns_sql}) VALUES ({placeholders});",
                to_db
            )
        except sqlite3.IntegrityError as e:
            print(f"IntegrityError while inserting into {tables[index]}: {e}")
        except sqlite3.OperationalError as e:
            print(f"OperationalError while inserting into {tables[index]}: {e}")

# Commit changes and close the connection
conn.commit()
conn.close()