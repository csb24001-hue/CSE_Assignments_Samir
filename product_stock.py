products = {
    "Laptop": 25,
    "Mouse": 8,
    "Keyboard": 5,
    "Monitor": 12,
    "Printer": 4
}

for product, stock in products.items():
    if stock < 10:
        print(product, stock)
