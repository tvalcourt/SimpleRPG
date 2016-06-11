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

    -- hp
    p_HP = player : getHP()
    e_HP = enemy : getHP()

    -- defence
    p_defence = player : getDefence()
    e_defence = enemy : getDefence()

	if(p_speed > e_speed) then
		enemy : setHP(e_HP - damageCheck(p_attack, e_defence))
		if(enemy : isAlive()) then
			player : setHP(p_HP - damageCheck(e_attack, p_defence))
		else
			enemy : defeated()
		end
	else
		player : setHP(p_HP - damageCheck(e_attack, p_defence))
		if(player : isAlive()) then
			enemy : setHP(e_HP - damageCheck(p_attack, e_defence))
		end
	end
end

-- Evaluates how much damage was dealt for this round of combat
function damageCheck(attack, defence)
	if(defence >= attack) then
		return 1
	else
		return (attack - defence)
	end
end