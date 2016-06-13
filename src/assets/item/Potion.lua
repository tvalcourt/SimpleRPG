--
-- Created by IntelliJ IDEA.
-- User: Trevor
-- Date: 6/12/2016
-- Time: 9:45 PM
-- To change this template use File | Settings | File Templates.
--
function use (player)
    p_curHitpoints = player : getCurrentHitpoints()
    p_maxHitpoints = player : getMaxHitpoints()
    POTION_HEALTH = 10

    -- TODO: Update so it removes the item from their inventory if used
    if(p_curHitpoints ~= p_maxHitpoints) then -- if the isn't at max health
        if((p_curHitpoints + POTION_HEALTH) > p_maxHitpoints) then -- check to see if adding 10 will go over max
            player : setCurrentHitpoints(p_maxHitpoints)
        else
            new_health = p_curHitpoints + POTION_HEALTH; -- if not, simply add 10 to the player
            player : setCurrentHitpoints(new_health)
        end
    else
        print("Unable to use Potion. Health already at max.")
    end
end

