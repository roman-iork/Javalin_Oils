@import java.util.Map
@import java.util.HashMap
@import java.util.LinkedList
@import java.math.BigDecimal
@import java.math.MathContext

@param HashMap<String, Double> weights
@param Map<String, Double> acids
@param LinkedList<String> selected
@param Map<String, Double> wR
@param HashMap<String, HashMap<String, Double>> oilsInfo


@template.layout(content = @`
    <div style="padding-left: 30px" class="rounded-rectangle-result-left">
        <h3 style="display: inline-block; padding: 10px">Oils</h3>
        @for(var oilWeight : weights.entrySet())
            <span style="display: flex">
                <span style="padding-right: 20px">${oilWeight.getKey()}</span>
                <div style="position: absolute; left: 30%">
                    <form style="display: inline; padding-left: 5px" action="/calculation/do/all?oil=${oilWeight.getKey()}&weight=${oilWeight.getValue() - 1.00}" method="post">
                        <button type="submit">-1</button>
                    </form>
                    <form style="display: inline; padding-left: 5px" action="/calculation/do/all?oil=${oilWeight.getKey()}&weight=${oilWeight.getValue() - 0.10}" method="post">
                        <button type="submit">-0.1</button>
                    </form>
                    <form style="display: inline; padding-left: 5px" action="/calculation/do/all?oil=${oilWeight.getKey()}&weight=${oilWeight.getValue() - 0.05}" method="post">
                        <button type="submit">-0.05</button>
                    </form>
                    <span style="padding-left: 5px">
                        ${BigDecimal.valueOf(oilWeight.getValue()).round(new MathContext(3))}
                    </span>
                </div>
                <div style="position: absolute; left: 50%">
                    <form style="display: inline; padding-left: 5px" action="/calculation/do/all?oil=${oilWeight.getKey()}&weight=${oilWeight.getValue() + 0.05}" method="post">
                        <button type="submit">+0.05</button>
                    </form>
                    <form style="display: inline; padding-left: 5px" action="/calculation/do/all?oil=${oilWeight.getKey()}&weight=${oilWeight.getValue() + 0.10}" method="post">
                        <button type="submit">+0.1</button>
                    </form>
                    <form style="display: inline; padding-left: 5px" action="/calculation/do/all?oil=${oilWeight.getKey()}&weight=${oilWeight.getValue() + 1.00}" method="post">
                        <button type="submit">+1</button>
                    </form>
                </div>
            </span>
        @endfor
        <div style="padding-top: 30px; padding-bottom: 30px; padding-left: 10px">
            <b>
                Total weight:
                ${BigDecimal.valueOf(weights.values().stream().mapToDouble(Double::doubleValue).sum()).round(new MathContext(4))}
            </b>
        </div>
        <span style="display: flex">
            @for(var oneOilInfo : oilsInfo.entrySet())
                <div style="width: 30%; font-size: 13px; position: relative; left: 0%">
                    <h4>
                        ${oneOilInfo.getKey()}
                        @if(selected != null)
                            (
                            @if(oneOilInfo.getValue().get(selected.get(0)) != null && oneOilInfo.getValue().get(selected.get(1)) != null)
                                ${BigDecimal.valueOf(oneOilInfo.getValue().get(selected.get(0)) / oneOilInfo.getValue().get(selected.get(1))).round(new MathContext(3))}
                            @endif
                            )
                        @endif
                    </h4>
                    @for(var acidPair : oneOilInfo.getValue().entrySet())
                        <span style="display: flex">
                            ${acidPair.getKey()}
                             <span style="position: absolute; left: 70%">
                                ${acidPair.getValue()}
                            </span>
                        </span>
                    @endfor
                </div>
            @endfor
        </span>
    </div>
`, content2 = @`
    <div style="padding-left: 30px" class="rounded-rectangle-result-right">
        <h3 style="display: inline-block; padding: 10px">Acids</h3>
        @if(selected == null)
            <form action="/calculation/do/all" method="post">
                @for(var acid : acids.entrySet())
                    <span style="display: flex">
                        <input type="checkbox" name="acid" value="${acid.getKey()}">
                        <label>
                            ${acid.getKey()}
                            <span style="position: absolute; left: 75%">${acid.getValue()}</span>
                        </label>
                    </span>
                @endfor
                <button type="submit">Submit</button>
            </form>
        @else
            <form action="/calculation/do/all" method="post">
                @for(var acid : acids.entrySet())
                    <span style="display: flex">
                        <input type="checkbox" name="acid" value="${acid.getKey()}" disabled>
                        <label>
                            ${acid.getKey()}
                            <span style="position: absolute; left: 75%">${acid.getValue()}</span>
                        </label>
                    </span>
                @endfor
                <button type="submit">Submit</button>
            </form>
        @endif
        @if(selected != null)
            <div style="padding-top: 40px">
                <span style="display: inline">
                    Ratio of
                    <b>${selected.get(0)}</b>
                    to
                    <b>${selected.get(1)}</b>
                    is
                    <b>${BigDecimal.valueOf(Double.parseDouble(selected.get(2))).round(new MathContext(3))}</b>
                </span>
                <form action="/calculation/do/all?clear=do" method="post">
                    <button type="submit">Clear chosen acids</button>
                </form>
                <form action="/calculation/do/all" method="post" style="padding-top: 30px">
                    Count automatically
                    <div>
                        <input type="text" name="weight" placeholder="Enter weight" value="${wR.getOrDefault("w", 15.0)}">
                        <input type="text" name="ratio" placeholder="Enter ratio" value="${wR.getOrDefault("r", 1.5)}">
                        <button type="submit">Do auto count</button>
                    </div>
                </form>
            </div>
        @endif
    </div>
`, content3 = @``)
