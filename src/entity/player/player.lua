function create (obj)
	obj:setMaxHitpoints (25)
    obj:setCurrentHitpoints(25)
	obj:setAttack (15)
	obj:setDefence (12)
	obj:setSpeed (14)
	obj:setExperience (0)
end

function battle (player, enemy)
    -- speed
    p_speed = player : getSpeed()
    e_speed = enemy : getSpeed()

    -- attack
    p_attack = player : getAttack()
    e_attack = enemy : getAttack()

    -- defence
    p_defence = player : getDefence()
    e_defence = enemy : getDefence()

    -- names
    p_name = player : getName()
    e_name = enemy : getName()

    print("===== Battle Starting! =====")

    if(p_speed > e_speed) then
        -- player attacks
        print(p_name, " attacks for ", damageCheck(p_attack, e_defence), " damage!")
        calc = enemy : getHitpoints() - damageCheck(p_attack, e_defence)
        enemy : setHitpoints(calc)

        if(enemy : isAlive()) then
            print(e_name, " has ", enemy : getHitpoints(), " hitpoints remaining!")
            print(e_name, " attacks for ", damageCheck(e_attack, p_defence), " damage!")
            calc = player : getCurrentHitpoints() - damageCheck(e_attack, p_defence)
            player : setCurrentHitpoints(calc)

            if(player : isAlive()) then
                print(p_name, " has ", player:getCurrentHitpoints(), " hitpoints remaining!")
            else
                player : defeated()
            end
        else
            print(e_name, " is defeated!")
            enemy : defeated()
        end
    else
        -- enemy attacks
        print(e_name, " attacks for ", damageCheck(e_attack, p_defence), " damage!")

        if(player : isAlive()) then
            print(p_name, " has ", enemy:getHitpoints(), " hitpoints remaining!")
            print(p_name, " attacks for ", damageCheck(p_attack, e_defence), " damage!")

            calc = enemy:getHitpoints() - damageCheck(p_attack, e_defence)
            enemy : setHitpoints(calc)

            if(enemy : isAlive()) then
                print(e_name, " has ", enemy:getHitpoints(), " hitpoints remaining!")
            else
                enemy : defeated()
            end
        else
            player : defeated()
        end
    end -- end combat

    print("===== Battle Complete =====")

end

-- Evaluates how much damage was dealt for this round of combat
function damageCheck(attack, defence)
	if(defence >= attack) then
		return 1
	else
		return (attack - defence)
	end
end