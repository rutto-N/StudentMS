let appNavBarLinks = {
    renderTo: "comp-topnav",
    links: [
        {
            label: "Report",
            id: "app.link.report",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlForm.render.apply(reportingForm.form);

            }
        },
        {
            label: "Enrolments",
            id: "app.link.enrolments",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlTable.render.apply(activeEnrolments);

            }
        },
        {
            label: "Grades",
            id: "app.link.grades",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlTable.render.apply(grades);

            }
        },{
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