from get_count_people_from_camera import count_people

def main():

#ЕГОР ПЕРЕДЕЛАЙ ЭТО

    results = {}


    camera_indices = [0, 1]

    for index in camera_indices:

        people_count = count_people(index)
        results[f"Camera {index}"] = people_count


    print("Результаты подсчета людей:")
    for camera, count in results.items():
        print(f"{camera}: {count} человек")

if __name__ == "__main__":
    main()