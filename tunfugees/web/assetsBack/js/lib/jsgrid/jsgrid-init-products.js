$(function () {

    $("#jsGrid").jsGrid({
        height: "100%",
        width: "100%",
        filtering: true,
        editing: true,
        inserting: false,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 15,
        pageButtonCount: 5,
        deleteConfirm: "Do you really want to delete this product?",
        controller: db_products,
        fields: [
            {
                name: "ID",
                visible: false,
                type: "text",
                width: 20
            },
            {
                name: "Picture",
                itemTemplate: function (value) {
                    return $("<img>").attr("src", value).css({height:70});
                },
                width: 100
            },
            {
                name: "Label",
                itemTemplate: function (value,item) {
                    return $("<a>").attr("href", Routing.generate('products_show_dashboard',{ id: item.ID})).text(value);
                },
                type: "text",
                width: 150,
                validate: {
                    validator:  "minLength",
                    message:    function(value,item){
                        return "The label must contains at least 5 characters.";
                    },
                    param: 5
                }
            },
            {
                name: "Category",
                type: "select",
                items: db_products.categories,
                valueField: "ID",
                textField: "Label",
                width: 100
            },
            {
                name: "Price",
                type: "number",
                validate: {
                    validator:  "min",
                    message:    function(value,item){
                        return "The price must be greater than zero.";
                    },
                    param: 1
                }
            },
            {
                name: "Quantity",
                type: "number",
                validate: {
                    validator:  "min",
                    message:    function(value,item){
                        return "The remaining quantity must be greater than zero.";
                    },
                    param: 1
                }
            },
            {
                type: "control"
            }
        ]
    });
});