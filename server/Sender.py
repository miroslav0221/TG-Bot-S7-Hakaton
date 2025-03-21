import requests
import time
import random

URL = "http://localhost:8081/update"

def send_data():
    while True:
        data = {
            "номер_стойки": random.randint(1, 10),  # Генерируем случайный номер стойки (1-10)
            "количество_человек": random.randint(0, 50)  # Генерируем случайное количество людей (0-50)
        }
        response = requests.post(URL, json=data)
        print(f"Sent: {data}, Response: {response.text}")
        time.sleep(5)  # Отправляем данные каждые 5 секунд

if __name__ == "__main__":
    send_data()