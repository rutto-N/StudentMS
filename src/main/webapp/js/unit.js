let unitForm = {
    url: "./units",
    method: "GET",
    id:'unit',
    tableTitle: 'Units',
    renderTo: 'componentRender',
    columns: [{
        header: "#",
        dataIndex: "id",

    },
        {
            header: "Code",
            dataIndex: "code",
        },
        {
            header: "Unit",
            dataIndex: "name",
        }],

    form: {
        formTitle: 'Unit',
        fields:
            [
                {
                    label: "course",
                    name: "courseId",
                    id: "courseId",
                    type: "select",
                    select:{
                        url:"./courses",
                        optionMap: {value: 'id', display: 'name'}
                    },
                    required: true
                },{
                label: "Year Of Study",
                name: "yearOfStudyId",
                id: "yearOfStudyId",
                type: "select",
                select:{
                    url:"./groups",
                    optionMap: {value: 'id', display: 'name'}
                },
                required: true
            },{
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
                    label:"Unit",
                    name:"name",
                    id:"name",
                    type:"text",
                    required:true,
                },
                {
                    label:"Unit Code",
                    name:"code",
                    id:"code",
                    type:"text",
                    required:true,
                },

            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './unit/new',
                value: 'Submit',
                showMsg: 'showErrorMsg',
                id: 'dept.submit',
                success: function () {
                    AppComponents.htmlTable.render.apply(unitForm);

                },
                failure: function () {
                    AppComponents.htmlForm.render.apply(unitForm.form);


                }
            }]


    },
    buttons: [{
        label: 'Add',
        id: 'unit.add',
        handler: function(){
            AppComponents.htmlForm.render.call(unitForm.form);
        }
    }]



}
