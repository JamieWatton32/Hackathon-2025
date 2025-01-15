import csv
import re
import sqlite3
import os

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
 #Function to sanitize column names
def sanitize_column_name(column_name):
    return re.sub(r'\W+', '_', column_name.strip())  # Replace non-word characters with underscores

# Create tables dynamically
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
        del sanitized_columns[0]
        # Construct CREATE TABLE statement dynamically
        column_definitions = "ID INTEGER PRIMARY KEY, " + ', '.join([f"{col} TEXT" for col in sanitized_columns])
         

        table_name = tables[index]
        create_table_sql = f"CREATE TABLE IF NOT EXISTS {table_name} ({column_definitions});"
        cur.execute(create_table_sql)

# Read CSV files and insert data
for index, csv_path in enumerate(csvs):
    with open(csv_path, 'r', encoding='utf-8-sig') as fin:
        dr = csv.DictReader(fin)
        
        # Sanitize and map column names
        original_columns = dr.fieldnames
        sanitized_columns = [sanitize_column_name(col) for col in original_columns]
        
        # Prepare placeholders and dynamic SQL query
        columns_sql = ', '.join(sanitized_columns)
        placeholders = ', '.join(['?' for _ in sanitized_columns])
        
        to_db_temp = []
        for row in dr:
            # Map original values to sanitized columns
            values = [row[col] for col in original_columns]
            to_db_temp.append(values)
        
        to_db = []
        for col in to_db_temp:
            temp =[]
            for str in col:
                cleaned_str = re.sub(r'[\[\]"]', "", str)
                temp.append(cleaned_str)
            to_db.append(tuple(temp))
               
        

        
        # Insert data into the correct table
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