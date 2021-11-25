let sessionForm = {
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
        formTitle: 'New Session',
        fields:
            [
                {
                    label: "Year Of Study",
                    name: "yearOfStudyId",
                    id: "yearOfStudyId",
                    type: "select",
                    select:{
                        url:"./groups",
                        optionMap: {value: 'id', display: 'name'}
                    },
                    required: true
                },

                {
                    label: "Academic year",
                    name: "academicYearId",
                    id: "academicYearId",
                    type: "select",
                    select:{
                        url:"./years",
                        optionMap: {value: 'id', display: 'year'}
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
                    label: "Units",
                    name: "unitId",
                    id: "unitId",
                    type: "checkbox",
                    check:{
                        url:"./units",
                        optionMap: {value: 'id', display: 'name'}
                    },
                    required: true
                },



            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './session/new',
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
