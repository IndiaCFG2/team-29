import firebase_admin
from firebase_admin import credentials
from firebase_admin import db, firestore

# Use a service account
cred = credentials.Certificate('C:/Users/RAHUL GUPTA/Desktop/team-29/cms_teacher/firebase-sdk.json')
firebase_admin.initialize_app(cred, {
	'databaseURL' : 'https://codeforgood-54cec.firebaseio.com/'
})

ref = db.reference('/')
db = firestore.client()

ref.set({

	'Teacher' : {
		'test_teacher_1' : {
			'teacher_name' : 'teach_1',
			'teacher_subject' : 'Political Science'
		},
		'test_teacher_2' : {
			'teacher_name' : 'teach_2',
			'teacher_subject' : 'Geography'
		}
	}

})