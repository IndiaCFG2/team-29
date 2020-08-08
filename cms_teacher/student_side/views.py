from django.shortcuts import render, redirect, HttpResponse
# from pyrebase import pyrebase

firebaseConfig = {
    'apiKey': "AIzaSyDxZwUxJl8Peq1puSWw1jpSQsDQx-sLk6I",
    'authDomain': "codeforgood-54cec.firebaseapp.com",
    'databaseURL': "https://codeforgood-54cec.firebaseio.com",
    'projectId': "codeforgood-54cec",
    'storageBucket': "codeforgood-54cec.appspot.com",
    'messagingSenderId': "953684615576",
    'appId': "1:953684615576:web:cb0ae9bb9d1e4bebcfcbed",
    'measurementId': "G-QEZS1EDTWK"
}

# firebase = pyrebase.intialize_app(firebaseConfig)


def setUpDatabase(request):
    return render(request, 'student_side/student_sign_up.html')
