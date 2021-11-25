let departmentForm = {
    url: "./departments",
    method: "GET",
    id:'dept',
    tableTitle: 'Departments',
    renderTo: 'componentRender',
    columns: [{
        header: "#",
        dataIndex: "id",

    },
        {
            header: "Name",
            dataIndex: "name",
        },
        ],

    form: {
        formTitle: 'New Department',
        fields:
            [
                {
                    label:"Department",
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
                url: './department/new',
                value: 'Submit',
                showMsg: 'showErrorMsg',
                id: 'dept.submit',
                success: function () {
                    AppComponents.htmlTable.render.apply(departmentForm);

                },
                failure: function () {
                    AppComponents.htmlTable.render.apply(departmentForm.form);


                }
            }]


    },
    buttons: [{
        label: 'Add',
        id: 'dept.add',
        handler: function(){
            AppComponents.htmlForm.render.call(departmentForm.form);
        }
    },
        {
            label: 'Remove',
            id: 'dept.remove',
            method:'GET',
            type:'deleteBtn',
            url:'./department-delete',
            handler: function(){
                AppComponents.htmlForm.render.apply(departmentForm.form);
            }
        }]



}
// AppComponents.htmlForm.render.call(departmentForm.form);