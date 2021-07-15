import React from 'react';

import { Spin } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';

interface Props {
    size?: number;
}

const Loader = ({ size = 24 }: Props) => {
    const antIcon = <LoadingOutlined style={{ fontSize: size }} spin />;

    return <Spin indicator={antIcon} />;
};

export default Loader;
