let reportingForm = {
    url: "./enrol/view",
    method: "GET",
    id:'id',
    tableTitle: 'Report',
    renderTo: 'componentRender',
    columns: [{
        header: "#",
        dataIndex: "enrolmentId",

    },
        {
            header: "Student",
            dataIndex: "studentName",
        },
        {
            header: "Unit",
            dataIndex: "courseName",
        }],

    form: {
        formTitle: 'Session reporting',
        fields:
            [
            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './enrol/new',
                value: 'Report',
                showMsg: 'showErrorMsg',
                id: 'student.submit',
                success: function () {
                    AppComponents.htmlTable.render.apply(reportingForm);

                },
                failure: function () {
                    console.log("failed");


                }
            }]


    },



}
