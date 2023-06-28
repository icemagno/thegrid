# https://pythonprogramming.net/custom-environment-reinforcement-learning-stable-baselines-3-tutorial/?completed=/saving-and-loading-reinforcement-learning-stable-baselines-3-tutorial/
# https://stable-baselines.readthedocs.io/en/master/guide/custom_env.html

# https://github.com/adderbyte/GYM_XPLANE_ML/blob/1a934908300e60a4c4c7dc7fbd1bba56694e3395/gym_xplane_final_version/gym_xplane/space_definition.py

import gym
from gym import spaces
import numpy as np
from collections import deque

class Mundo(gym.Env):
    # Cannot implement the GUI ('human' render mode)
    metadata = {'render.modes': ['console']}

    # Domain of controls
    WEST = 0
    EAST = 1
    NORTH = 2
    SOUTH = 3

    def __init__( self, lat=-23.5, lon=-43.5 ):
        super(Mundo, self).__init__()
        self.hist_buffer = 1000
    	# Define action and observation space
    	# They must be gym.spaces objects
    	# Example when using discrete actions:
        self.action_space = spaces.Discrete(4)
        self.agent_start_pos = [lat,lon]
        self.agent_pos = [lat,lon]
    	# Example for using image as input (channel-first; channel-last also works):
        self.observation_space = spaces.Box(low=np.array([-180.0, -90.0]), high=np.array([180, 90]), dtype=np.float32)
    
    def step(self, action):
        self.prev_actions.append(action)
        
        old_lat = self.agent_pos[0]
        old_lon = self.agent_pos[1]
        
        if action == self.NORTH:
          self.agent_pos[0] += 0.5
        elif action == self.SOUTH:
          self.agent_pos[0] -= 0.5
        elif action == self.EAST:
          self.agent_pos[1] += 0.5
        elif action == self.WEST:
          self.agent_pos[1] -= 0.5
        else:
          raise ValueError("Received invalid action={} which is not part of the action space".format(action))
    
        # Account for the boundaries of the grid
        if self.agent_pos[0] < -90:
            self.agent_pos[0] = -90
        if self.agent_pos[0] > 90:
            self.agent_pos[0] = 90

        if self.agent_pos[1] < -180:
            self.agent_pos[1] = 180
        if self.agent_pos[1] > 180:
            self.agent_pos[1] = -180
            
        # Are we at the center of the globe?
        self.done = bool(  (self.agent_pos[0] == 0) and (self.agent_pos[1] == 0) )
        
        self.reward = 0

        if ( self.agent_pos[0] == 0 or self.agent_pos[1] == 0 ):
            self.reward = 0.9

        if self.agent_pos[0] < old_lat:
            if self.agent_pos[0] > 0:
                self.reward = 0.5
        if self.agent_pos[0] > old_lat:
            if self.agent_pos[0] < 0:
                self.reward = 0.5
                
        if self.agent_pos[1] < old_lon:
            if self.agent_pos[1] > 0:
                self.reward = 0.5
        if self.agent_pos[1] > old_lon:
            if self.agent_pos[1] < 0:
                self.reward = 0.5

        if self.done:
            self.reward = 1
                
        # Optionally we can pass additional info, we are not using that for now
        info = {"latitude":self.agent_pos[0],"longitude":self.agent_pos[1]}

        lat = self.agent_pos[0]
        lon = self.agent_pos[1]

        observation = [lat,lon] # + list(self.prev_actions)
        observation = np.array(observation)
        return observation, self.reward, self.done, info
    
    
    def reset(self):
        # Initialize the agent at the initial coordinates
        self.agent_pos = [-23.5,-43.5]
        self.done = False
        self.prev_actions = deque(maxlen = self.hist_buffer)
        for i in range(self.hist_buffer):
            self.prev_actions.append(-1)
        lat = self.agent_pos[0]
        lon = self.agent_pos[1]
        observation = [lat,lon] # + list(self.prev_actions)
        observation = np.array(observation)
        return observation
        
    def render(self, mode='console'):
        if mode != 'console':
          raise NotImplementedError()
        
    def close (self):
        pass

