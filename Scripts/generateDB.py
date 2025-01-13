import csv
import sqlite3
import os

# Define database file path
db_path = "sqlite/database.db"

# Check if database folder exists, create if not
os.makedirs(os.path.dirname(db_path), exist_ok=True)

try:
    # Establish a database connection
    con = sqlite3.connect(db_path)
    cur = con.cursor()
    
    # Create tables
    tables = ["Classrooms", "Culinary", "Laboratory", "Maintenance", "Offices", "Shops"]
    csvs = ["data/Classroom Procedure.csv", "data/Culinary Procedure.csv","data/Laboratory Procedure.csv", "data/Maintenance Procedure.csv", "data/Office Procedure.csv","data/Shop Procedure.csv"]
  # Create tables
    for table in tables:
        cur.execute(f"CREATE TABLE IF NOT EXISTS {table} (id INTEGER, room TEXT, date TEXT);")

# Read CSV files and insert data
    for index, csv_path in enumerate(csvs):
        if not os.path.exists(csv_path):
            raise FileNotFoundError(f"CSV file not found at {csv_path}")
        
        with open(csv_path, 'r', encoding='utf-8-sig') as fin:
            dr = csv.DictReader(fin)  # Comma is default delimiter
            to_db = [(i['id'], i['Location of inspection'], i["Date of inspection"]) for i in dr]
            
            # Insert data into the correct table
            try:
                cur.executemany(f"INSERT INTO {tables[index]} (id, room, date) VALUES (?, ?, ?);", to_db)
            except sqlite3.IntegrityError as e:
                print(f"IntegrityError while inserting into {tables[index]}: {e}")
            except sqlite3.OperationalError as e:
                print(f"OperationalError while inserting into {tables[index]}: {e}")
        # Commit changes
        con.commit()

except sqlite3.Error as e:
    print(f"SQLite error occurred: {e}")
except FileNotFoundError as e:
    print(f"File error: {e}")
except Exception as e:
    print(f"Unexpected error: {e}")
finally:
    # Close the database connection
    if 'con' in locals():
        con.close()
    print("Database connection closed.")
