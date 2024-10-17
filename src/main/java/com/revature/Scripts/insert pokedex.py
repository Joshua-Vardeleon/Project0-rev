import psycopg2
import json
class Pokemon:
    def __init__(self, poke_no, poke_name, type1, type2):
        self.poke_no = poke_no
        self.poke_name = poke_name
        self.type1 = type1
        self.type2 = type2

with open('pokedex.json', 'r', encoding="utf-8") as file:
    data = json.load(file)

print(len(data[0]['type']))



conn = psycopg2.connect(database="postgres",
                        host="localhost",
                        user="postgres",
                        password="password",
                        port="5432",
                        options="-c search_path=projectzero")

cur = conn.cursor()
for x in data:
    if len(x['type']) == 2:
        cur.execute("INSERT INTO pokedex(poke_no,pokemon_name,pokemon_type1,pokemon_type2) "
                    + f"VALUES({x['id']}, '{x['name']['english']}', '{x['type'][0]}', '{x['type'][1]}')")
    else:
        cur.execute("INSERT INTO pokedex(poke_no,pokemon_name,pokemon_type1) "
                    + f"VALUES({x['id']}, '{x['name']['english']}', '{x['type'][0]}')")

conn.commit()
conn.close()