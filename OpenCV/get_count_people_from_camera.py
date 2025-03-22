import cv2
import numpy as np

net = cv2.dnn.readNetFromCaffe(
    "AImodel/deploy.prototxt",
    "AImodel/mobilenet_iter_73000.caffemodel"
)

CLASSES = ["background", "aeroplane", "bicycle", "bird", "boat",
           "bottle", "bus", "car", "cat", "chair", "cow", "diningtable",
           "dog", "horse", "motorbike", "person", "pottedplant",
           "sheep", "sofa", "train", "tvmonitor"]

def count_people(camera_index):

    cap = cv2.VideoCapture(camera_index)

    if not cap.isOpened():
        print(f"Ошибка: Не удалось открыть камеру с индексом {camera_index}.")
        return 0

    ret, frame = cap.read()
    if not ret:
        print(f"Ошибка: Не удалось получить кадр с камеры {camera_index}.")
        cap.release()
        return 0


#    (h, w) = frame.shape[:2]

    blob = cv2.dnn.blobFromImage(cv2.resize(frame, (300, 300)), 0.007843, (300, 300), 127.5)
    net.setInput(blob)
    detections = net.forward()

    people_count = 0


    for i in range(detections.shape[2]):
        confidence = detections[0, 0, i, 2]

        if confidence > 0.2:
            idx = int(detections[0, 0, i, 1])

            if CLASSES[idx] == "person":
                people_count += 1

    cap.release()
    return people_count