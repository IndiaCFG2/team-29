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
	return render(request, 'teacher_side/teacher_login.html')

def teacher_log_in_view(request):
	if request.method == 'POST':
		email = request.POST.get('email')
		passw = request.POST.get('passw')
		try :
			user = auth.sign_in_with_email_and_password(email, passw)
		except :
			message = "Invalid credentials"
			return render(request, 'teacher_side/teacher_login.html', {'message' : message})
		return render(request, "teacher_side/teacher_dashboard.html", {"teacher_name" : email})
	return render(request, "teacher_login.html")
