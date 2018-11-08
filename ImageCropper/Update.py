import Classify as cl
import sys
import mysql.connector
import Details as D

if len(sys.argv) == 1:
    mydb = mysql.connector.connect(
        host=D.hostn,
        user=D.usern,
        passwd=D.passw,
        database=D.dbname
    )
    mycur = mydb.cursor()
    sql = "SELECT LOT_ID FROM PARKING_LOTS"
    mycur.execute(sql)
    myresult = mycur.fetchall()
    for x in myresult:
        cl.classify(x[0])
else:
    for x in sys.argv:
        if x != "Update.py":
            cl.classify(x)
