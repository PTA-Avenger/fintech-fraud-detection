import csv
import random
from faker import Faker
from datetime import datetime, timedelta

fake = Faker()
FILENAME = "transactions.csv"
NUM_TRANSACTIONS = 20

TRAPS = [
    {"date": "2026-02-14 10:00:00", "merchant": "Crypto-mixer-X", "amount": "500.00", "location": "Unknown"},
    {"date": "2026-02-15 03:15:00", "merchant": "Rolex Sandton", "amount": "15000.00", "location": "Sandton SA"}
]

def generate_random_transaction():
    date_obj = datetime.now() - timedelta(days=random.randint(0, 30))
    date_obj = date_obj.replace(hour=random.randint(8, 20), minute=random.randint(0, 59))
    
    # SAFE FIX: Remove commas from generated names so Java split(',') doesn't break
    merchant = fake.company().replace(",", "")
    location = fake.city().replace(",", "")
    
    return [
        date_obj.strftime("%Y-%m-%d %H:%M:%S"),
        merchant, 
        round(random.uniform(50.0, 4500.0), 2), 
        location
    ]

print(f"Generating {FILENAME}...")

with open(FILENAME, mode='w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(["Date", "Merchant", "Amount", "Location"])
    
    for _ in range(NUM_TRANSACTIONS):
        writer.writerow(generate_random_transaction())
        
    for trap in TRAPS:
        writer.writerow([trap["date"], trap["merchant"], trap["amount"], trap["location"]])
        print(f"-> Injected TRAP: {trap['merchant']}")

print("Done! File ready.")