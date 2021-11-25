let groupForm = {
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
        formTitle: 'New Group',
        fields:
            [
                {
                    label:"Group",
                    name:"name",
                    id:"name",
                    type:"text",
                    required:true,
                },



            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './group/new',
                value: 'Submit',
                showMsg: 'showErrorMsg',
                id: 'dept.submit',
                success: function () {
                    console.log("Success");

                },
                failure: function () {
                    console.log("failed");


                }
            }]


    },



}
// AppComponents.htmlForm.render.call(departmentForm.form);