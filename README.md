# Neochloris - Plant Disease Detection

Neochloris is a plant disease detection platform that helps farmers and gardeners identify and address plant diseases using advanced deep learning models. The main feature of this project allows users to scan plants directly using their camera to diagnose potential diseases.

**Demo Preview**
<p align="center">
  <img  src="images/Fullpage.png" width="1000">
</p>

## Features
- **Plant Disease Detection:** Users can upload images or use their camera to capture images of plants. The system analyzes these images to detect potential diseases.
- **Disease Library:** Provides a library of common plant diseases with detailed information on symptoms, prevention, and treatment.
- **Real-time Plant Analysis:** Using the camera, users can instantly analyze their plants and receive results.

## Technologies Used
This project utilizes various technologies across different layers:

### Backend
- **Python:** For backend logic and deep learning model training.
- **FastAPI:** For building the backend API to handle requests from the frontend.
- **MySQL:** For storing disease data and user information.
- **TensorFlow/Keras:** For deep learning model used to predict plant diseases.
- **NumPy:** For handling numerical operations in model prediction.

### Frontend
- **JavaScript (with `getUserMedia` API):** For accessing the user's camera to capture plant images.
- **HTML/CSS:** For building the structure and style of the web pages.
- **Java:** For frontend application development.
- **IntelliJ IDEA:** IDE used to run the frontend application.

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
   - Open your terminal in VS Code and navigate to the `backend` directory.
   - Run the backend application:
     ```bash
     python app.py
     ```

3. **Frontend Setup**  
   - Open your project in IntelliJ IDEA.
   - Run the frontend application using IntelliJ's "Run" button.

## Usage
- Open the frontend application in your browser.
- You can either upload an image of a plant or use your camera to scan the plant.
- The system will analyze the image and provide a prediction on the plant's health, including possible diseases and their treatments.

## Requirements
- **Backend:**
  - Python 3.x
  - FastAPI
  - MySQL
  - TensorFlow
  - NumPy

- **Frontend:**
  - JavaScript (with `getUserMedia` API)
  - HTML/CSS

- **Tools:**
  - IntelliJ IDEA
  - VS Code
  - XAMPP (for Apache and MySQL)

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


