function create (obj)
	obj:setHitpoints (25)
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

    -- Hitpoints
    p_Hitpoints = player : getHitpoints()
    e_Hitpoints = enemy : getHitpoints()

    -- defence
    p_defence = player : getDefence()
    e_defence = enemy : getDefence()

	if(p_speed > e_speed) then
		enemy : setHitpoints(e_Hitpoints - damageCheck(p_attack, e_defence))
		if(enemy : isAlive()) then
			player : setHitpoints(p_Hitpoints - damageCheck(e_attack, p_defence))
		else
			enemy : defeated()
		end
	else
		player : setHitpoints(p_Hitpoints - damageCheck(e_attack, p_defence))
		if(player : isAlive()) then
			enemy : setHitpoints(e_Hitpoints - damageCheck(p_attack, e_defence))
		end
	end

	player : displayStats()
	enemy : displayStats()
end

-- Evaluates how much damage was dealt for this round of combat
function damageCheck(attack, defence)
	if(defence >= attack) then
		return 1
	else
		return (attack - defence)
	end
end