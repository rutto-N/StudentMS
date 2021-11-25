let yearForm = {
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
                label: "Academic Year",
                name: "yearId",
                id: "yearId",
                type: "select",
                select:{
                    url:"./years",
                    optionMap: {value: 'id', display: 'year'}
                },
                required: true
            }
            ],
        buttons:
            [{
                type: 'submit',
                method: "POST",
                url: './open',
                value: 'Submit',
                showMsg: 'showErrorMsg',
                id: 'year.submit',
                success: function () {
                    alert("Successfully");

                },
                failure: function () {


                }
            }]


    },
    buttons: [
    ]



}
