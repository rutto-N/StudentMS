let academicYearForm = {
    url: "./enrol/view",
    method: "GET",
    id:'dept',
    tableTitle: 'Enrolments',
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
        formTitle: 'Academic Year ',
        fields:
            [
                {
                    label:"Start Date",
                    name:"startDate",
                    id:"startDate",
                    type:"date",
                    required:true,
                },
                {
                    label:"End Date",
                    name:"endDate",
                    id:"endDate",
                    type:"date",
                    required:true,
                },

            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './year/new',
                value: 'Submit',
                showMsg: 'showErrorMsg',
                id: 'year.submit',
                success: function () {
                    console.log("Success");

                },
                failure: function () {
                    console.log("failed");


                }
            }]


    },



}
// AppComponents.htmlForm.render.call(courseForm.form);