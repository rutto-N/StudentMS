var AppComponents = {
    htmlForm:{
        render: function(){
            /* this method renders dynamic html form */

            //declaring this object to me, so that we can reference to it, even when we are not within this.
            let me = this;

            //html to render form
            let formToRender = '<h2>' + me.formTitle + '</h2>';

            formToRender += '<form >';

            //loop through the form fields and construct form input fields in html
            me.fields.forEach(field=>{
                if (field.type === 'select'){
                    formToRender += '<label for="' + field.id +'">' + field.label
                        + (field.required?'<span style="color: red;">*</span>':'') + ':</label>' +
                        '<div class="form-group">'
                        + '<select class="form-control-sm" name="' + field.name + '" id="' + field.id + '">' +
                        ' <option disabled selected value="">------Select '+field.label+'-----</option>--%>';

                      let selectOptions;
                      if (field.select && field.select.data){
                        selectOptions = field.select.data;

                      }else if (field.select && field.select.url){

                            let ajaxReq = new XMLHttpRequest();
                            ajaxReq.onreadystatechange = function(){
                                if (ajaxReq.readyState === XMLHttpRequest.DONE && ajaxReq.status === 200) {
                                    selectOptions = eval('(' + ajaxReq.responseText + ')');
                                    selectOptions = selectOptions.list;
                                }
                            }

                            ajaxReq.open('get', field.select.url, false);
                            ajaxReq.send();

                      }

                      selectOptions.forEach(option =>{
                         formToRender += '<option value="' + option[field.select.optionMap.value]+ '">' + option[field.select.optionMap.display] + '</option>';
                      })

                      formToRender += '</select></div>';

                }
                else if(field.type === 'checkbox'){
                    formToRender+='<div class="form-group">'
                    formToRender += '<label  for="' + field.id +'">' + field.label
                        + (field.required?'<span style="color: red;">*</span>':'') + ':</label>'


                    let checkOptions;
                    if (field.check && field.check.data){
                        checkOptions = field.check.data;

                    }else if (field.check && field.check.url){
                        var ajaxReq = new XMLHttpRequest();
                        ajaxReq.onreadystatechange = function(){
                            if (ajaxReq.readyState === XMLHttpRequest.DONE && ajaxReq.status === 200) {
                                checkOptions = eval('(' + ajaxReq.responseText + ')');
                                checkOptions = checkOptions.list;
                            }
                        }

                        ajaxReq.open('get', field.check.url, false);
                        ajaxReq.send();

                    }
                    console.log(checkOptions);
                    formToRender+='<div class="form-group">'
                    checkOptions.forEach(opt =>{
                        formToRender += '<input class="form-control-sm" id="'+field.id+'" type="checkbox" name="'+field.name+
                            '" value="' + opt[field.check.optionMap.value]+ '">'
                        +opt[field.check.optionMap.display];

                    });
                    formToRender+='</div>'

                    formToRender+='</div>'


                }
                else{
                    formToRender+='<div class="form-group">'
                    formToRender += '<label for="' + field.id +'">' + field.label
                        + (field.required?'<span style="color: red;">*</span>':'') + ':</label>'
                        +'<input  class="form-control-sm" type="' + field.type + '" id="' + field.id +'" name="' + field.name + '"><br>';
                    formToRender+='</div>'

                }


            });

            console.log(formToRender);

            formToRender += '';

            //add buttons to form render
            me.buttons.forEach(btn=>{
                formToRender += '<input type="' + btn.type + '" value="' + btn.value + '" id="' + btn.id + '"></form>';
            });

            //render form in html div with id 'componentRender'
            document.getElementById('componentRender').innerHTML = formToRender;

            //loop through the buttons again and add event listeners, modifying url, method, showMsg, success function, failure fucntion
            me.buttons.forEach(btn=>{
                document.getElementById(btn.id).addEventListener("click",  event=>{
                    event.preventDefault();

                    me.url = btn.url;
                    me.method = btn.method;
                    me.showMsg = btn.showMsg;
                    me.success = btn.success; // will execute if saving is success
                    me.failure = btn.failure; //will execute if saving is failure

                    AppComponents.htmlForm.submit.apply(me);

                });
            });

        },
        submit: function(){
            /* this method submit a form through ajax */

            //declaring this object to me, so that we can reference to it, even when we are not within this.
            let me = this;

            //data to be submitted will be populated in this variable
            let submitData = '';

            //flag to check if form is clean to be submitted
            let submitForm = true;

            //loop through the form to be submitted and collect the values while populating the submitForm variable
            me.fields.forEach(field=>{
                if (field.type === 'checkbox'){
                    const checkboxes = document.querySelectorAll('input[name="'+field.name+'"]:checked');
                    let inputs = [];
                    checkboxes.forEach((checkbox) => {
                        inputs.push(checkbox.value);
                    });
                    console.log(inputs);
                    inputs.forEach(input=>{
                        submitData += encodeURIComponent(field.name) + '=' + encodeURIComponent(input) + '&';

                    });


                }else {
                    let fieldVal = document.getElementById(field.id).value;
                    if (field.required === true && !fieldVal)
                        submitForm = false;

                    submitData += encodeURIComponent(field.name) + '=' + encodeURIComponent(fieldVal) + '&';

                }


            });

            if (!submitForm){
                document.getElementById(me.showMsg).innerHTML = 'Please Enter All Required Fields(*)';
                return;
            }

            //ajax component
            var ajaxReq = new XMLHttpRequest();
            ajaxReq.onreadystatechange = function(){
                if (ajaxReq.readyState === XMLHttpRequest.DONE && ajaxReq.status === 200) {
                    let reqRes = eval('(' + ajaxReq.responseText + ')');
                    if (!reqRes.success)
                        document.getElementById(me.showMsg).innerHTML = reqRes.message;
                    else if (reqRes.redirectPage)
                        location.href = reqRes.redirectPage;
                    else if (reqRes.success) {
                        let msg = reqRes.message;
                        alert(msg);
                        me.success();
                    }
                    else if (reqRes.failure)
                        me.failure();


                }
            }

            ajaxReq.open(me.method, me.url, false);
            ajaxReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
            ajaxReq.send(submitData);

        }
    },
    htmlTable: {
        render: function(){
            /* this method render html page */

            let me = this;
            let tableToRender = '<h2>' + me.tableTitle + '</h2>';
            let tableId = me.tableRefId;

            me.buttons.forEach(btn=>{
                tableToRender += '<button class="app-btn green-btn" type="button" id="' + btn.id + '">' + btn.label + '</button>';
            });

            tableToRender += '<br/><br/><table class="table table-bordered" id="'+tableId+'">';

            let tableColGroup = '<colgroup>';
            let tableHeaders = '<thead class="thead-light"><tr>';

            tableColGroup += '<col span="1" style="width: 3%">';
            tableHeaders += '<th></th>';

            me.columns.forEach(col=>{
                tableColGroup += '<col span="' + (col.span?col.span: 1) + '" style="'+ (col.width? 'width:' + col.width + '%;': '') + '">';
                tableHeaders += '<th>' + col.header + '</th>';

            });

            tableColGroup += '</colgroup>';
            tableHeaders += '</tr></thead>';

            tableToRender += tableColGroup + tableHeaders;

            tableToRender += '<tbody>';

            //load page from html
            var ajaxReq = new XMLHttpRequest();
            ajaxReq.onreadystatechange = function(){
                if (ajaxReq.readyState === XMLHttpRequest.DONE && ajaxReq.status === 200) {
                    let reqRes = eval('(' + ajaxReq.responseText + ')').list;
                    reqRes.forEach(row => {

                        tableToRender += '<tr><td><input type="checkbox" name="name1" id="'+row.id +'"/>&nbsp;</td>';
                        console.log("checkbox ids" + row.id);


                        me.columns.forEach(col => {
                            tableToRender += '<td>' + row[col.dataIndex] + '</td>';
                        });
                        tableToRender += '</tr>';
                        console.log(tableToRender);

                    });

                }
            }

            ajaxReq.open(me.method, me.url, false);
            ajaxReq.send();

            tableToRender += '</tbody>'
            document.getElementById(me.renderTo).innerHTML = tableToRender;

            me.buttons.forEach(btn=>{
                if (btn.type === "deleteBtn"){
                    document.getElementById(btn.id).addEventListener("click", function (e) {
                        e.preventDefault();
                        let tableReference = document.getElementById(tableId);
                        let tbody = tableReference.querySelector("tbody");
                        let selectedBoxes = document.querySelectorAll("input[type='checkbox']:checked");
                            if (selectedBoxes.length >= 0) {
                                Array.prototype.slice.call(selectedBoxes).forEach(input => tbody.removeChild(input.parentNode.parentNode))
                                selectedBoxes.forEach(valueId => {
                                    alert("Are you sure you want to delete?");
                                    let reqResp = request('GET', btn.url + '?id=' + valueId.id, false);
                                    if (reqResp.success) {
                                        alert(reqResp.message);
                                    }
                                });
                            } else {
                                alert("Please Select A valid row");
                            }
                    });

                }
                else
                    document.getElementById(btn.id).addEventListener("click", btn.handler);
            });

        }
    },
    htmlToNavBar: {
        render: function(){
            let me = this;

            let topNavToolBar = '';

            me.links.forEach(link=>{
                topNavToolBar += '<a class="' + link.class + '" id="' + link.id + '" href="#">' + link.label + '</a>';
            });

            document.getElementById(me.renderTo).innerHTML = topNavToolBar;

            me.links.forEach(link=>{
                document.getElementById(link.id).addEventListener("click", link.handler);
            });

        },
        changeStyle: function(linkId){
            let me = this;
            console.log(me);
            console.log(linkId);

            me.links.forEach(link=>{
                if (link.id === linkId){
                    document.getElementById(linkId).classList.add("active");

                }else{
                   document.getElementById(linkId).classList.remove("active");

               }
            });
        },
        loadSessionData: function(userDataLink){
            let userSessionData = {};

            var ajaxReq = new XMLHttpRequest();
            ajaxReq.onreadystatechange = function(){
                if (ajaxReq.readyState == XMLHttpRequest.DONE){
                    if (ajaxReq.status == 200){
                        let reqRes = eval('(' + ajaxReq.responseText + ')');

                        reqRes.list.forEach(row=>{
                            userSessionData = row;
                        });
                    }
                }
            }

            ajaxReq.open('get', userDataLink, false);
            ajaxReq.send();


            return userSessionData;

        }
    }
};

function request(method, url, submitData) {
    let resp = '';
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function () {
        if (xmlHttpRequest.readyState == XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status == 200) {
                resp = eval('(' + xmlHttpRequest.responseText + ')');
            } else {
                alert("Try again " + xmlHttpRequest.responseText)
            }
        }
    }
    xmlHttpRequest.open(method, url, false);
    if (submitData == null) {
        xmlHttpRequest.send();
    } else {xmlHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
        xmlHttpRequest.send(submitData);
    }
    return resp;
}



