$(function () {

    $("#jsGrid").jsGrid({
        height: "100%",
        width: "100%",
        filtering: true,
        editing: false,
        inserting: false,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 15,
        pageButtonCount: 5,
        deleteConfirm: "Do you really want to ban this user?",
        controller: db_users,
        fields: [
            {name: "ID", visible:false, type: "text", width: 150},
            {name: "Username",itemTemplate: function(value,item) {
                    return $("<a>").attr("href", Routing.generate("user_profile_dashboard", { id: item.ID})).text(value);
                }, type: "text", width: 50},
            {name: "E-mail", type: "text", width: 150},
            {name: "Nationality", type: "text", width: 100},
            {name: "First Name", type: "text"},
            {name: "Last Name", type: "text"},
            {type: "control"}
        ]
    });

});