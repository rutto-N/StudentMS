let appNavBarLinks = {
    renderTo: "comp-topnav",
    userDataLink:'./login',
    links: [
        {
            label: "Department",
            id: "app.link.dept",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlTable.render.apply(departmentForm);

            }
        },
        {
            label: "Course",
            id: "app.link.course",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlTable.render.apply(courseForm);

            }
        },
        {
            label: "Year",
            id: "app.link.yearOfStudy",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlForm.render.apply(groupForm.form);

            }
        },
        {
            label: "Semester",
            id: "app.link.semester",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlTable.render.apply(semesterForm);

            }
        },
        {
            label: "Unit",
            id: "app.link.unit",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlTable.render.apply(unitForm);

            }
        },
        {
            label: "Lec Units",
            id: "app.link.unit-reg",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlForm.render.apply(lecUnitForm.form);

            }
        },
        {
            label: "Student",
            id: "app.link.student",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlForm.render.apply(studentForm.form);

            }
        },
        {
            label: "Lecturer",
            id: "app.link.lecturer",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlForm.render.apply(lecturerForm.form);

            }
        },
        {
            label: "Academic Year",
            id: "app.link.year",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlForm.render.apply(academicYearForm.form);

            }
        },
        {
            label: "Open",
            id: "app.link.open",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlForm.render.apply(yearForm.form);

            }
        },
        {
            label: "Logout",
            id: "app.link.logout",
            handler: function () {
                let userSessionData = AppComponents.htmlToNavBar.loadSessionData('./logout');
                if (!userSessionData.sessionId)
                    location.href = './';

            }
        },


    ]
};

AppComponents.htmlToNavBar.render.call(appNavBarLinks);