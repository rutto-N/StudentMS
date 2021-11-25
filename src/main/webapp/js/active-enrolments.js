let activeEnrolments = {
    url: "./student-enrolments",
    method: "GET",
    id:'active-enrolments',
    tableTitle: 'Active Enrolments',
    renderTo: 'componentRender',
    tableRefId:'activeEnrolments',
    columns: [{
        header: "#",
        dataIndex: "enrolmentId",
    },
        {
            header: "Name",
            dataIndex: "unitName",
        },
        {
            header: "Course",
            dataIndex: "courseName",
        }
    ],

    buttons: [

    ]
}
