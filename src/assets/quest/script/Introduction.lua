--
-- Created by IntelliJ IDEA.
-- User: Trevor
-- Date: 6/13/2016
-- Time: 9:09 PM
-- To change this template use File | Settings | File Templates.
--

item_rewards = {{a = "Gold", b = "50"}}

function init (quest)
    quest : setActive(false)
    quest : setAvailable(false)
    quest : setCompleted(false)
    quest : setExperienceReward(100)
end
