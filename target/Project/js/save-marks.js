let examForm = {
    url: "./semesters",
    method: "GET",
    id:'semester',
    tableTitle: 'Semesters',
    renderTo: 'componentRender',
    columns: [{
        header: "#",
        dataIndex: "id",
        width:2

    },
        {
            header: "Name",
            dataIndex: "name",
        }],

    form: {
        formTitle: 'Exam ',
        fields:
            [

                {
                    label: "Unit",
                    name: "unitId",
                    id: "unitId",
                    type: "select",
                    select:{
                        url:"./lec-units",
                        optionMap: {value: 'id', display: 'code'}
                    },
                    required: true
                },

            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './enrol-per-unit',
                value: 'Submit',
                showMsg: 'showErrorMsg',
                id: 'marks.submit',
                success: function () {
                    // AppComponents.htmlTable.render.apply(examForm);

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
            AppComponents.htmlTable.render.apply(examForm);;
        }
    }]



}
