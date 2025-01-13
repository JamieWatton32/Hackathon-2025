import csv
import sqlite3

con = sqlite3.connect("sqlite/database.db") # change to 'sqlite:///your_filename.db'
cur = con.cursor()
cur.execute("CREATE TABLE Classrooms (id TEXT, room TEXT);") # use your column names here

with open('data/Classroom Procedure.csv', 'r', encoding='utf-8-sig') as fin: # `with` statement available in 2.5+
    # csv.DictReader uses first line in file for column headings by default
    dr = csv.DictReader(fin) # comma is default delimiter
    to_db = [(i['id'], i['Location of inspection']) for i in dr]

cur.executemany("INSERT INTO Classrooms (id, room) VALUES (?, ?);", to_db)
con.commit()
con.close()