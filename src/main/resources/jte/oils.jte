@import model.Oil
@import java.util.List
@param List<String> data
@param List<String> calc


@template.layout(content = @`
    <div style="padding-left: 30px" class="rounded-rectangle-for-oils">
        <h3>Oils:</h3>
        @for(var oil : data)
            <span style="display: flex">
                <a href="/oils/${oil}" style="padding-left: 20px">${oil}</a>
                <form style="display: inline; position: absolute; left: 40%" action="/delete/${oil}" method="post" class="rightish">
                    <label for="options">delete oil</label>
                    <button type="submit">Delete</button>
                </form>
                @if(!calc.contains(oil))
                    <form style="display: inline; position: absolute; left: 70%" action="/calculation/${oil}" method="post">
                        <label for="options">add to calculation</label>
                        <button type="submit">Add</button>
                    </form>
                @endif
            </span>
        @endfor
    </div>
`, content2 = @`
    @if(!calc.isEmpty())
        <div class="rounded-rectangle-for-calc">
            <div style="padding-left: 30px">
                <h3>Oils for calculation</h3>
                @for(var oilCalc : calc)
                    <span style="display: flex">
                        ${oilCalc}
                        <form style="display: inline; position: absolute; right: 10%" action="/calculation/remove/one/${oilCalc}" method="post" class="rightish">
                            <button type="submit">Remove</button>
                        </form>
                    </span>
                @endfor
                <form style="display: flex; padding-top: 20px; position: absolute; right: 10%" action="/calculation/remove/all" method="post" class="rightish">
                    <button type="submit">Remove all</button>
                </form>
                <form style="display: flex; padding-top: 40px" action="/calculation/do/all" method="post" class="rightish">
                    <button type="submit">Calculate</button>
                </form>
            <div>
        </div>
    @endif
`, content3 = @``)
