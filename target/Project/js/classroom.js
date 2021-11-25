let classRoomForm = {
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
        formTitle: 'New Class Room',
        fields:
            [
                {
                    label: "Department",
                    name: "departmentId",
                    id: "departmentId",
                    type: "select",
                    select:{
                       url:"./departments",
                        optionMap: {value: 'id', display: 'name'
                       }
                    },
                    required: true
                },
                {
                    label: "Course",
                    name: "courseId",
                    id: "courseId",
                    type: "select",
                    select:{
                        url:"./courses",
                        optionMap: {value: 'id', display: 'name'}
                    },
                    required: true
                },
                {
                    label: "Group",
                    name: "groupId",
                    id: "groupId",
                    type: "select",
                    select:{
                        url:"./groups",
                        optionMap: {value: 'id', display: 'name'}
                    },
                    required: true
                },
                {
                    label: "Academic year",
                    name: "yearId",
                    id: "yearId",
                    type: "select",
                    select:{
                        url:"./years",
                        optionMap: {value: 'id', display: 'year'}
                    },
                    required: true
                },
                {
                    label: "Semester",
                    name: "semesterId",
                    id: "semesterId",
                    type: "select",
                    select:{
                        url:"./semesters",
                        optionMap: {value: 'id', display: 'name'}
                    },
                    required: true
                },
                {
                    label: "Unit",
                    name: "unitId",
                    id: "unitId",
                    type: "select",
                    select:{
                        url:"./units",
                        optionMap: {value: 'id', display: 'code'}
                    },
                    required: true
                },



            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './classroom/new',
                value: 'Save',
                showMsg: 'showErrorMsg',
                id: 'classroom.submit',
                success: function () {
                    console.log("Success");

                },
                failure: function () {
                    console.log("failed");


                }
            }]


    },



}
