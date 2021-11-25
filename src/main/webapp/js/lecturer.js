let lecturerForm = {
    url: "./enrol/view",
    method: "GET",
    id:'id',
    tableTitle: 'Classes',
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
        formTitle: 'New Lecturer',
        fields:
            [
                {
                    label: "Lecturer Id",
                    name: "lecturerId",
                    id: "lecturerId",
                    type: "text",
                    required: true
                },
                {
                    label: "FirstName",
                    name: "firstName",
                    id: "firstName",
                    type: "text",
                    required: true
                },
                {
                    label: "LastName",
                    name: "lastName",
                    id: "lastName",
                    type: "text",
                    required: true
                },
                {
                    label: "Email Address",
                    name: "email",
                    id: "email",
                    type: "email",
                    required: true
                },
                {
                    label: "Gender",
                    name: "genderStr",
                    id: "genderStr",
                    type: "select",
                    select:{
                        data:[
                            {
                                name: 'MALE', gender: 'Male'
                            },
                            {
                                name: 'FEMALE', gender: 'Female'
                            },
                        ],
                        optionMap: {value: 'name', display: 'gender'}
                    },
                    required: true
                },

                {
                    label: "Date Of Birth",
                    name: "dob",
                    id: "dob",
                    type: "date",
                    required: true
                },
                {
                    label: "Phone Number",
                    name: "phoneNumber",
                    id: "phoneNumber",
                    type: "text",
                    required: true
                },



            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './lecturer/new',
                value: 'Save',
                showMsg: 'showErrorMsg',
                id: 'student.submit',
                success: function () {
                    AppComponents.htmlTable.render.apply(departmentForm);

                },
                failure: function () {
                    AppComponents.htmlForm.render.apply(departmentForm.form);


                }
            }]


    },



}
