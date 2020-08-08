from django.shortcuts import render, redirect, HttpResponse
import pyrebase
from django.contrib import auth
firebaseConfig = {
	  'apiKey': "AIzaSyDxZwUxJl8Peq1puSWw1jpSQsDQx-sLk6I",
	  'authDomain': "codeforgood-54cec.firebaseapp.com",
	  'databaseURL': "https://codeforgood-54cec.firebaseio.com",
	  'projectId': "codeforgood-54cec",
	  'storageBucket': "codeforgood-54cec.appspot.com",
	  'messagingSenderId': "953684615576"
	}

firebase = pyrebase.initialize_app(firebaseConfig)
authe = firebase.auth()

def setUpDatabase(request):
	return render(request, 'home.html')

def teacher_signup_view(request):
	if request.method == 'POST':
		name = request.POST.get('username')
		email = request.POST.get('email')
		subject = request.POST.get('subject')
		number = request.POST.get('number')
		password = request.POST.get('password')
		user = authe.create_user_with_email_and_password(email, password)
		message = "User created"
		return render(request, "teacher_dashboard.html", {'message' : message})
	else:
		return render(request, "teacher_sign_up.html")

def teacher_log_in_view(request):
	if request.method == 'POST':
		email = request.POST.get('email')
		passw = request.POST.get('passw')
		try :
			user = authe.sign_in_with_email_and_password(email, passw)
		except :
			message = "Invalid credentials"
			return render(request, 'teacher_login.html', {'message' : message})
		print(user['idToken'])
		session_id = user['idToken']
		request.session['uid'] = str(session_id)
		return render(request, "teacher_dashboard.html", {"teacher_name" : email})
	return render(request, "teacher_login.html")

def logout(request):
	auth.logout(request)
	return render(request, 'home.html')
