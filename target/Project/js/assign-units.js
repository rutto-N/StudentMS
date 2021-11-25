let lecUnitForm = {
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
        formTitle: 'Lecturer Units',
        fields:
            [
                {
                    label: "Lecturer",
                    name: "lecturerId",
                    id: "lecturerId",
                    type: "select",
                    select:{
                        url:"./lecturers",
                        optionMap: {value: 'id', display: 'email'}
                    },
                    required: true
                },
                {
                    label: "Units",
                    name: "unitId",
                    id: "unitId",
                    type: "checkbox",
                    check:{
                        url:"./active-units",
                        optionMap: {value: 'id', display: 'code'
                        }
                    },
                    required: true
                }

            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './lecturer/units',
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
