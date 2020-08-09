from django.shortcuts import render, redirect, HttpResponse
import pyrebase
from django.contrib import auth
from firebase_admin import db
import random
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


def setUpDatabase(request):
    return render(request, 'home.html')

def teacher_dashboard(request):
	return render(request, "teacher_dashboard.html")

def teacher_signup_view(request):
	if request.method == 'POST':
		name = request.POST.get('username')
		email = request.POST.get('email')
		subject = request.POST.get('subject')
		number = request.POST.get('number')
		password = request.POST.get('password')
		user = authe.create_user_with_email_and_password(email, password)
		uid = user['localId']
		data = {"teacher_name" : name, "teacher_email" : email, "teacher_subject" : subject, "phone_number" : number, "password" :  password}
		database.child("Teacher").child(uid).set(data)
		return render(request, "teacher_dashboard.html", {'teacher_name' : str(name)})
	else:
		return render(request, "teacher_sign_up.html")

def teacher_log_in_view(request):
    if request.method == 'POST':
        email = request.POST.get('email')
        passw = request.POST.get('passw')
        try:
            user = authe.sign_in_with_email_and_password(email, passw)
        except:
            message = "Invalid credentials"
            return render(request, 'teacher_login.html', {'message': message})
        print(user['idToken'])
        session_id = user['idToken']
        request.session['uid'] = str(session_id)
        return render(request, "teacher_dashboard.html", {"teacher_name": email})
    return render(request, "teacher_login.html")


def logout(request):
    auth.logout(request)
    return render(request, 'home.html')


def schedule(request):
    if request.method == 'POST':
        date = request.POST.get('date')
        desc = request.POST.get('description')
        classroom = request.POST.get('classroom')
        time = request.POST.get('time')
        uid = random.randint(0, 100000000000000000)
        data = {'event_date': date, 'event_time': time,
                'event_desc': desc, 'classroom': classroom}
        database.child("Events").child(uid).set(data)
        message = "success"
        return render(request, "teacher_dashboard.html", {'message': message})
    else:
        return render(request, "calendar.html")


def view_schedules(request):
    databaseInstance = firebase.database().child("Events").get().each()
    dic = []
    for i in databaseInstance:
        temp = i.val()
        obj = {'date': temp['event_date'], 'time': temp['event_time'],
               'desc': temp['event_desc'], 'classroom': temp['classroom']}
        dic.append(obj)
    return render(request, 'view_schedules.html', {'schedule': dic})


def discussion_tab(request):
    databaseInstance = firebase.database().child("Doubts").get().each()
    dic = []
    for i in databaseInstance:
        temp = i.val()
        obj = {'doubt_ans': temp['answer'], 'doubt_desc': temp['doubts'],
               'doubt_id': temp['id'], 'name': temp['name']}
        dic.append(obj)
    return render(request, 'discussion_tab.html', {'questions': dic})


def submit_answer(request):
    if(request.method == 'POST'):
        ans = request.POST.get('answer')
        uid = request.POST.get('uid')
        database.child("Doubts").child(uid).child('answer').set(ans)
        return render(request, 'teacher_dashboard.html')
    return render(request, 'discussion_tab.html')
