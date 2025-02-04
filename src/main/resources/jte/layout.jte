@import gg.jte.Content

@param Content content
@param Content content2
@param Content content3

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Oils site</title>
    <style>
        body {
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #a5806e; /* Light brown */
        }

        .container {
            width: 95%;
            height: 95%;
            background-color: #a5806e; /* Same as main background */
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        .horizontal-bar {
            width: 100%;
            height: 8%;
            background-color: #d5a100; /* Yellow */
            position: absolute;
            left: 0%;
            top: 5%;
            display: flex;
            align-items: center; /* Vertically center the form */
            justify-content: flex-start; /* Align the form to the left */
            padding-left: 20px; /* Optional: Add some space from the left */
        }

        .rounded-rectangle {
            width: 70%;
            height: 35%;
            background-color: #c6c3a5; /* Light gray-beige */
            border-radius: 15px;
            position: absolute;
            left: 0%;
            top: 40%;
            transform: translateY(-50%);
        }

        .rounded-rectangle-second {
            width: 25%;
            height: 20%;
            background-color: #a0b7ba; /* Light gray-beige */
            border-radius: 15px;
            position: absolute;
            right: 0%;
            top: 35%;
            transform: translateY(-50%);
        }

        .rounded-rectangle-third {
            width: 25%;
            height: 35%;
            background-color: #ccc7bc; /* Light gray-beige */
            border-radius: 15px;
            position: absolute;
            left: 0%;
            top: 80%;
            transform: translateY(-50%);
        }

        .rounded-rectangle-for-oils {
            width: 60%;
            height: 80%;
            background-color: #ccc7bc; /* Light gray-beige */
            border-radius: 15px;
            position: absolute;
            left: 0%;
            top: 55%;
            transform: translateY(-50%);
        }

        .rounded-rectangle-for-calc {
            width: 25%;
            height: 80%;
            background-color: #a0b7ba; /* Light gray-beige */
            border-radius: 15px;
            position: absolute;
            right: 5%;
            top: 55%;
            transform: translateY(-50%);
        }

        .rounded-rectangle-result-left {
            width: 75%;
            height: 80%;
            background-color: #c9c899; /* Light gray-beige */
            border-radius: 15px;
            position: absolute;
            left: 0%;
            top: 55%;
            transform: translateY(-50%);
        }

        .rounded-rectangle-result-right {
            width: 18%;
            height: 80%;
            background-color: #b2bfaa; /* Light gray-beige */
            border-radius: 15px;
            position: absolute;
            right: 0%;
            top: 55%;
            transform: translateY(-50%);
        }

        .rightish {
            padding-right: 40px;
        }

        .leftish {
                    padding-left: 5px;
        }
    </style>
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
        <div style="display: inline-block">
            ${content}
            ${content2}
            ${content3}
        </div>
    </div>
</body>

</html>
