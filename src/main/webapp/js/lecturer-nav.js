let appNavBarLinks = {
    renderTo: "comp-topnav",
    links: [
        {
            label: "Exam",
            id: "app.link.exam",
            handler: function () {
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlForm.render.apply(examForm.form);

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