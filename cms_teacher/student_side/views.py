from django.shortcuts import render, redirect, HttpResponse
import pyrebase
import random
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
database = firebase.database()

def student_login(request):
	if request.method == 'POST':
		email = request.POST.get('email')
		passw = request.POST.get('passw')
		try :
			user = authe.sign_in_with_email_and_password(email, passw)
		except :
			message = "Invalid credentials"
			return render(request, 'student_login.html', {'message' : message})
		print(user['idToken'])
		session_id = user['idToken']
		request.session['uid'] = str(session_id)
		return render(request, "student_dashboard.html", {"student_name" : email})
	return render(request, "student_login.html")



def student_signup(request):
	if request.method == "POST":
		name = request.POST.get('name')
		email = request.POST.get('mail')
		number = request.POST.get('number')
		claSS = request.POST.get('class')
		password = request.POST.get('password')
		user = authe.create_user_with_email_and_password(email, password)
		uid = user['localId']
		data = {"classname" : claSS, "email" : email, "id" : uid, "password" :  password, "phonenumber" : number}
		database.child("Students").child(uid).set(data)
		message = "User created"
		return render(request, "student_dashboard.html")
	else:
		return render(request, 'student_signup.html')


def logout(request):
	auth.logout(request)
	return render(request, 'home.html')

def student_dashboard(request):
	if request.method == "POST":
		pass
def doubts_submission(request):
	if request.method == "POST":
		name = request.POST.get('name')
		doubt = request.POST.get('doubt')
		uid = random.randint(0, 100000000000000000)
		data = {'answer' : '', 'doubts' : doubt, 'name' : name, 'id' : uid}
		database.child("Doubts").child(uid).set(data)
		return render(request, "student_dashboard.html")
	else:
		return render(request, "doubts.html")

def student_dashboard(request):
	return render(request, "student_dashboard.html")
