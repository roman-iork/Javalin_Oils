@import transfer.OilTransfer
@import java.util.List
@param List<OilTransfer> data
@param List<String> acidsData


@template.layout(content = @`
    <div style="padding-left: 30px" class="rounded-rectangle">
        <h3 style="display: inline-block; padding: 10px">${data.get(0).getOilName()}</h3>

        <form style="display: inline; padding-left: 150px" action="/${data.get(0).getOilName()}/acids" method="post" class="rightish">

            <label for="options">acid</label>
            <select id="options" name="acid_name">
                @for(var acidName : acidsData)
                    <option value="${acidName}">${acidName}</option>
                @endfor
            </select>

            <label style="padding-left: 20px">acid %</label>
            <input type="text" name="acid_amount" placeholder="Enter acid amount">
            <button type="submit">Add</button>
        </form>
        @for(var oilData : data)
            @if(oilData.getAcidName() != null)
                <span style="display: flex">
                    <span class="rightish">
                        acid:
                        <span class="leftish">${oilData.getAcidName()}</span>
                    </span>
                    <span class="rightish" style="position: absolute; left: 30%">
                        acid %:
                        <span class="leftish">
                            ${oilData.getAcidAmount()}
                        </span>
                    </span>
                    <div style="position: absolute; left: 50%">
                        <form action="/delete/${data.get(0).getOilName()}/${oilData.getAcidName()}" method="post" style="display: inline; padding-left: 60px">
                            <label>Delete acid</label>
                            <button type="submit">Delete</button>
                        </form>
                    </div>
                </span>
            @endif
        @endfor
    </div>
`, content2 = @`
     <div class="rounded-rectangle-second">
         <div style="padding-left: 30px; padding-top: 20px">
              <form style="display: inline; padding-left: 30px" action="/acids?oil=${data.get(0).getOilName()}" method="post" class="rightish">
                  <label>Create acid:</label>
                  <input type="text" name="acid_name" placeholder="Enter acid name">
                  <button type="submit">Create</button>
              </form>
         </div>
     </div>
`, content3 = @`
    <div>
        @if(data.get(0).getAcidAmount() != 0)
            <div class="rounded-rectangle-third" style="padding-left: 30px">
                <h2>${data.get(0).getOilName()}</h2>
                @for(var oilData : data)
                    <span style="display: flex">
                        ${oilData.getAcidName()}
                        <span style="position: absolute; left: 50%">
                            ${oilData.getAcidAmount()}
                        </span>
                    </span>
                @endfor
            </div>
        @endif
    </div>
`)
