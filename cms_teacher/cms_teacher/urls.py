"""cms_teacher URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from teacher_side import views as teacher_side_views
from student_side import views as student_side_views
urlpatterns = [
	path('', include('teacher_side.urls')),
    path('admin/', admin.site.urls),
    path('teacher_login/', teacher_side_views.teacher_log_in_view, name = 'teacher_login'),
    path('teacher_signup/', teacher_side_views.teacher_signup_view, name = 'teacher_signup'),
    path('logout/', teacher_side_views.logout, name = "logout"),
    path('student_login/', student_side_views.student_login, name = 'student_login'),
    path('student_signup/', student_side_views.student_signup, name = 'student_signup'),
    path('schedule/', teacher_side_views.schedule, name = 'schedule'),
    path('view_schedules/', teacher_side_views.view_schedules, name = "view_schedules"),
    path('discussion_tab/', teacher_side_views.discussion_tab, name = "discussion_tab"),
    path('submit_answer/', teacher_side_views.submit_answer, name = "submit_answer"),
    path('teacher_dashboard/', teacher_side_views.teacher_dashboard, name = "teacher_dashboard"),
    path('doubts_submission/', student_side_views.doubts_submission, name = "doubts_submission"),
    path('student_dashboard/', student_side_views.student_dashboard, name = "student_dashboard")
]