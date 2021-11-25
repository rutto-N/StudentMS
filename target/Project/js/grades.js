let grades = {
    url: "./student-scores",
    method: "GET",
    id:'grades',
    tableTitle: 'Grades',
    renderTo: 'componentRender',
    tableRefId:'grades',
    columns: [{
        header: "#",
        dataIndex: "id",
    },
        {
            header: "Name",
            dataIndex: "unitName",
        },
        {
            header: "Grade",
            dataIndex: "grade",
        }
    ],

    buttons: [

    ]
}
