from django.shortcuts import render, redirect, HttpResponse
import pyrebase

firebaseConfig = {
	  'apiKey': "AIzaSyDxZwUxJl8Peq1puSWw1jpSQsDQx-sLk6I",
	  'authDomain': "codeforgood-54cec.firebaseapp.com",
	  'databaseURL': "https://codeforgood-54cec.firebaseio.com",
	  'projectId': "codeforgood-54cec",
	  'storageBucket': "codeforgood-54cec.appspot.com",
	  'messagingSenderId': "953684615576"
	}

firebase = pyrebase.initialize_app(firebaseConfig)
auth = firebase.auth()

def setUpDatabase(request):
	return render(request, 'teacher_side/teacher_sign_up.html')

def teacher_sign_up_view(request):
	if request.method == 'POST':
		email = request.POST.get('email')
		passw = request.POST.get('passw')

		user = auth.sign_in_with_email_and_password(email, passw)
		return render(request, "teacher_side/teacher_dashboard.html", {"teacher_name" : email})
	return render(request, "teacher_sign_up.html")
