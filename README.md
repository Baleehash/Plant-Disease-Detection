# Neochloris - Plant Disease Detection

Neochloris is a plant disease detection platform that helps farmers and gardeners identify and address plant diseases using advanced deep learning models. The main feature of this project allows users to scan plants directly using their camera to diagnose potential diseases.

**Demo Preview**
<p align="center">
  <img  src="\Frontend\plantapp\src\main\resources\static\images\Fullpage.png" width="1000">
</p>

## Features
- **Plant Disease Detection:** Users can upload images or use their camera to capture images of plants. The system analyzes these images to detect potential diseases.
- **Disease Library:** Provides a library of common plant diseases with detailed information on symptoms, prevention, and treatment.
- **Real-time Plant Analysis:** Using the camera, users can instantly analyze their plants and receive results.

**Disease Library**
<p align="center">
  <img  src="\Frontend\plantapp\src\main\resources\static\images\disease-library.png" width="1000">
</p>

**Result for Upload**
<p align="center">
  <img  src="\Frontend\plantapp\src\main\resources\static\images\result-upload.png" width="1000">
</p>

**Result for Real-time Capture**
<p align="center">
  <img  src="\Frontend\plantapp\src\main\resources\static\images\result-capture.png" width="1000">
</p>

## Technologies Used
This project utilizes various technologies across different layers:

### Backend
- [![Python][python-badge]][Python-url]
- [![FastAPI][fastapi-badge]][FastAPI-url]
- [![MySQL][mysql-badge]][MySQL-url]  
- [![TensorFlow][tensorflow-badge]][TensorFlow-url] 

[python-badge]: https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white
[Python-url]: https://www.python.org
[fastapi-badge]: https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=fastapi&logoColor=white
[FastAPI-url]: https://fastapi.tiangolo.com
[mysql-badge]: https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white
[MySQL-url]: https://www.mysql.com
[tensorflow-badge]: https://img.shields.io/badge/TensorFlow-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white
[TensorFlow-url]: https://www.tensorflow.org

### Frontend
- [![JavaScript][javascript-badge]][JavaScript-url]
- [![HTML][html-badge]][HTML-url]  
- [![CSS][css-badge]][CSS-url]  
- [![Java][java-badge]][Java-url]  

[javascript-badge]: https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black
[JavaScript-url]: https://www.javascript.com
[html-badge]: https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white
[HTML-url]: https://developer.mozilla.org/en-US/docs/Web/HTML
[css-badge]: https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white
[CSS-url]: https://developer.mozilla.org/en-US/docs/Web/CSS
[java-badge]: https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.oracle.com/java/

### Development Environment
- **Apache:** Web server used to serve the frontend.
- **MySQL (via XAMPP):** Database for storing plant disease information.
- **VS Code:** IDE for backend development.
- **IntelliJ IDEA:** IDE for frontend development.
- **Postman**: For testing API endpoints.

## Setup and Installation

1. **Start Apache and MySQL**  
   Launch XAMPP and start both Apache and MySQL servers.

2. **Backend Setup**  
   - Open terminal in VS Code and navigate to the `backend` directory.
   - Run the backend application:
     ```bash
     python app.py
     ```

3. **Frontend Setup**  
   - Open project (Frontend Folder) in IntelliJ IDEA.
   - Run the frontend application using IntelliJ's "Run" button.

## Usage
- Open the frontend application in your browser.
- You can either upload an image of a plant or use your camera to scan the plant.
- The system will analyze the image and provide a prediction on the plant's health, including possible diseases and their treatments.

## Stable Versions
- Python: 3.8.x
- FastAPI: 0.95.x
- MySQL: 8.0.x
- TensorFlow: 2.x
- NumPy: 1.24.x

## License
This project is open-source under the [MIT license](https://opensource.org/licenses/MIT).

---

If you have any questions, feel free to reach out!


