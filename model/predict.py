import sys
import numpy as np
from keras.models import load_model
from PIL import Image
import os

model = load_model('./model/my_model.h5')

def process_image(image_path):
    """Обработка изображения по пути к файлу"""
    try:
        if not os.path.exists(image_path):
            return f"Error: File {image_path} not found"

        image = Image.open(image_path)
        image = image.convert('RGB').resize((224, 224))
        image_array = np.array(image) / 255.0
        image_array = np.expand_dims(image_array, axis=0)

        pred = model.predict(image_array)
        return ['здоров', 'пневмония', 'covid'][np.argmax(pred)]

    except Exception as e:
        return f"Error: {str(e)}"

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python predict.py <image_path>")
        sys.exit(1)

    result = process_image(sys.argv[1])
    print(result)