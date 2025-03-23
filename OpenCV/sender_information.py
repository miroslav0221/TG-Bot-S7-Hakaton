from get_count_people_from_camera import count_people
import json, requests, time

def main():
    while True:
        results = {}

        camera_indices = {
            "Camera from computer" : 0,
            "Camera from first phone" : "http://10.9.33.204:8080/video",
            "Camera from second phone" : "http://192.168.0.100:8080/video"
        }

        for name, source in camera_indices.items():
            people_count = count_people(source)
            results[name] = people_count


        data = {
                "Толмачево": {
                    1: 0,
                    2: 0,
                    3: 0
                }
                # "Шереметьево": {
                #     1: 0,
                #     2: 0,
                #     3: 0
                # },
                # "Внуково": {
                #     1: 0,
                #     2: 0,
                #     3: 0
                # },
                # "Домодедово": {
                #     1: 0,
                #     2: 0,
                #     3: 0
                # },
                # "Пулково": {
                #     1: 0,
                #     2: 0,
                #     3: 0
                # },
                # "Казань": {
                #     1: 0,
                #     2: 0,
                #     3: 0
                # },
        }

        for airport in data:
            data[airport][1] = results.get("Camera from computer", 0)
            data[airport][2] = results.get("Camera from first phone", 0)
            data[airport][3] = results.get("Camera from second phone", 0)
        url = "http://localhost:8081/update"
        headers = {"Content-Type": "application/json; charset=utf-8"}

        try:
            response = requests.post(url, json=data, headers=headers)
            response.raise_for_status()
            print("Data successfully sent to the server")
            print("server response: ", response.text)
        except requests.exceptions.RequestException as e:
            print("Error when sending data to the server: ", e)
        time.sleep(5)

if __name__ == "__main__":
    main()
