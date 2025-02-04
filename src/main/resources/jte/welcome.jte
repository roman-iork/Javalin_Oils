<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Oils site</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <div class="horizontal-bar">
                <form action="/oil" method="post" class="rightish">
                    <label>Find oil:</label>
                    <input type="text" name="oil_name" placeholder="Enter oil name">
                    <button type="submit">Find</button>
                </form>
                <form action="/oils" method="post" class="rightish">
                    <label>Add oil:</label>
                    <input type="text" name="oil_name" placeholder="Enter oil name">
                    <button type="submit">Add</button>
                </form>
                <form action="/oils" method="get">
                    <label>See all oils:</label>
                    <button type="submit">See</button>
                </form>
            </div>
        </div>
    </body>
</html>
