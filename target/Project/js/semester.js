let semesterForm = {
    url: "./semesters",
    method: "GET",
    id:'semester',
    tableTitle: 'Semesters',
    renderTo: 'componentRender',
    columns: [
        {
        header: "#",
        dataIndex: "id",
        width:2

    },
        {
            header: "Name",
            dataIndex: "name",
        }],

    form: {
        formTitle: 'Semester ',
        fields:
            [
                {
                    label:"Semester",
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
                url: './semester/new',
                value: 'Submit',
                showMsg: 'showErrorMsg',
                id: 'dept.submit',
                success: function () {
                    AppComponents.htmlTable.render.apply(semesterForm);

                },
                failure: function () {
                    console.log("failed");


                }
            }]


    },
    buttons: [{
        label: 'Add',
        id: 'semester.add',
        handler: function(){
            AppComponents.htmlForm.render.call(semesterForm.form);
        }
    },
        {
            label: 'Remove',
            id: 'semester.remove',
            method:'GET',
            type:'deleteBtn',
            url:'./semester-delete',
            handler: function(){
                AppComponents.htmlForm.render.apply(semesterForm.form);
            }
        }
    ]
}
