import sqlite3

# Connect to the database
conn = sqlite3.connect("sqlite/database.db")
cursor = conn.cursor()

# Drop all tables in one go
cursor.executescript("""
    PRAGMA foreign_keys = OFF;
    BEGIN TRANSACTION;
    SELECT 'DROP TABLE IF EXISTS ' || name || ';' FROM sqlite_master WHERE type='table';
    COMMIT;
""")
conn.close()
print("All tables dropped.")
