let courseForm = {
    url: "./courses",
    method: "GET",
    id:'dept',
    tableTitle: 'Courses',
    renderTo: 'componentRender',
    tableRefId:'courseTableId',
    columns: [{
        header: "#",
        dataIndex: "id",
    },
        {
            header: "Name",
            dataIndex: "name",
        },
        {
            header: "Department",
            dataIndex: "departmentName",
        }
        ],

    form: {
        formTitle: 'New Course',
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
                    label:"Course",
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
                url: './course/new',
                value: 'Submit',
                showMsg: 'showErrorMsg',
                id: 'dept.submit',
                success: function () {
                    AppComponents.htmlTable.render.apply(courseForm);
                },
                failure: function () {
                    AppComponents.htmlForm.render.apply(courseForm);
                }
            }]
    },
    buttons: [
        {
        label: 'Add',
        id: 'course.add',
        handler: function(){
            AppComponents.htmlForm.render.call(courseForm.form);
        }
    },
        {
            label: 'Remove',
            id: 'course.remove',
            method:'GET',
            type:'deleteBtn',
            url:'./course-delete',
            handler: function(){
                AppComponents.htmlForm.render.apply(courseForm.form);
            }
        }
    ]
}
